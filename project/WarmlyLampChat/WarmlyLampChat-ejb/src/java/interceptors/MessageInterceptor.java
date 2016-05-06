package interceptors;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import models.chat.Room;
import services.impl.MessageProcessor;

@Interceptor
public class MessageInterceptor {

    @Inject
    private MessageProcessor messageProcessor;

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        int idRoom = ((Room) context.getParameters()[0]).getId();
        String messageText = (String) context.getParameters()[2];
        messageProcessor.processMessage(idRoom, messageText);
        return context.proceed();
    }
}

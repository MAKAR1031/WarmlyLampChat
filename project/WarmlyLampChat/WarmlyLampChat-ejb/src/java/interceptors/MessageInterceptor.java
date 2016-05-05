package interceptors;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import services.impl.MessageProcessor;

@Interceptor
public class MessageInterceptor {

    @Inject
    private MessageProcessor messageProcessor;

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
        int idRoom = (int) context.getParameters()[1];
        String messageText = (String) context.getParameters()[2];
        messageProcessor.processMessage(idRoom, messageText);
        return context.proceed();
    }
}

param (
	$users_count, 
	$users_role,
	[switch]$clear_db
)

if ($clear_db) {
	Write-Host "Очистка базы от тестовых данных..."
	mysql.exe -uroot -proot -e "DELETE FROM WARMLY_LAMP_CHAT_CHAT_DB.USERS WHERE NICKNAME LIKE 'login_%'"
	return
}

$result = mysql.exe -uroot -proot -e "SELECT * FROM WARMLY_LAMP_CHAT_CHAT_DB.ROLES"
$roles = @{}
$result | select -Skip 1 | %{
	$role = $_.split("`t");
	$roles.($role[1]) = $role[0];
}

$last_login = mysql.exe -uroot -proot -e "SELECT NICKNAME FROM WARMLY_LAMP_CHAT_CHAT_DB.USERS WHERE ID=(SELECT MAX(ID) FROM WARMLY_LAMP_CHAT_CHAT_DB.USERS WHERE NICKNAME LIKE 'login_%');"
if ($last_login -eq $null) {
	$last_login_num = 0
} else {
	$last_login_num = [int]($last_login[1].split("_")[1])
}

Write-Host "Генерация скрипта и данных пользователей..."
$query = "INSERT INTO WARMLY_LAMP_CHAT_CHAT_DB.USERS(FIO,NICKNAME,PASSWORD,ROLE_ID) VALUES `n"


$csv_data = @()
($last_login_num + 1)..($last_login_num + $users_count) |  %{
	$login = "login_$_"
	$role_id = $roles.($users_role)
	$query += "('Тестовый Пользователь','$login',sha2('$login',256),$role_id)"
	if ($_ -eq ($last_login_num + $users_count)) {$query += ";"} else {$query += ",`n"}
	$csv_data += "$login;$login"
}

Write-Host "Сохранение SQL скрипта..."
$folder_name = "gererated_data"
if (Test-Path $folder_name) {rm $folder_name -r}
md $folder_name | Out-Null
$query | Out-File ".\$folder_name\script.sql" -Encoding UTF8

Write-Host "Сохранение пользовательских данных в формате CSV..."
$utf8_without_bom = New-Object System.Text.UTF8Encoding($false)
[System.IO.File]::WriteAllLines(".\$folder_name\user_data.csv", $csv_data, $utf8_without_bom)

Write-Host "Выполнение SQL скрипта..."
mysql.exe -uroot -proot -e "source .\$folder_name\script.sql" --default-character-set=UTF8
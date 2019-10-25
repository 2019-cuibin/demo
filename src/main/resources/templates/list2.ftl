<html>
<title>用户信息列表</title>
<body>
    <h1>Freemarker模板引擎</h1>
    <table border="1" bgcolor="#7cfc00">
        <thead>
            <tr>
                <th>id</th>
                <th>用户名</th>
                <th>密码</th>
            </tr>
        </thead>
        <#list User as user>
            <tr>
                <th>${user.id}</th>
                <th>${user.username}</th>
                <th>${user.password}</th>
            </tr>
        </#list>
    </table>
</body>
</html>
<#import "parts/pageTemplate.ftl" as p>
<@p.page cssFile="css/homepage.css">

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>

<div class="login-wrapper">
    <form action="/login" method="post" class="login-form">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        <h3 class="text-center mb-4">Login</h3>

        <div class="mb">
            <input type="text" name="username" class="form-control rounded-top py-3" placeholder="Username" required>
        </div>

        <div class="mb-3">
            <input type="password" name="password" class="form-control rounded-bottom py-3" placeholder="Password" required>
        </div>

        <button type="submit" class="btn btn-primary w-100">Log in</button>
    </form>
</div>

</body>
</html>

</@p.page>

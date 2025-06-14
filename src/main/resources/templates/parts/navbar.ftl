<!-- Подключи Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />

<div class="container">
    <nav class="navbar navbar-expand-lg bg-transparent" style="padding: 1% 0%;">
        <div class="container-fluid p-0">
            <a class="navbar-brand m-0" href="/"><b>[SPELS]</b></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Переключатель навигации">
            <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-center" id="navbarSupportedContent">
                <ul class="navbar-nav mx-auto text-start mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/" style="padding: 20px 60px 20px 3px;">ГЛАВНАЯ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/products"
                            style="padding: 20px 60px 20px 3px;">КАТАЛОГ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/services"
                            style="padding: 20px 60px 20px 3px;">УСЛУГИ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/contacts"
                            style="padding: 20px 60px 20px 3px;">КОНТАКТЫ</a>
                    </li>
                </ul>
            </div>
            <!-- Иконка входа справа -->
            <div class="d-none d-lg-flex align-items-center ms-auto">
                <#if isLoggedIn>
                    <!-- Иконка выхода -->
                    <a href="/logout" class="nav-link px-3" title="Logout">
                      <i class="fas fa-sign-out-alt" style="font-size: 20px; color: #dfd9b9;"></i>
                    </a>
                  <#else>
                    <!-- Иконка входа -->
                    <a href="/login" class="nav-link px-3" title="Login">
                      <i class="fas fa-user" style="font-size: 20px; color: #dfd9b9;"></i>
                    </a>
                </#if>
            </div>
        </div>
    </nav>
</div>
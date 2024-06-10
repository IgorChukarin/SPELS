<#import "parts/pageTemplate.ftl" as p>
<@p.page cssFile="css/products.css">
<br>
<div class="container" id="container2">
    <div class="row row-cols-1 row-cols-md-3 g-4;">
        <#list products as product>
            <div class="col">
                <a href="https://media.tenor.com/JowtY-iElqUAAAAd/cat-fisheye.gif" class="card">
                    <img src="${product.imagePath}" loading="lazy" class="card-img-top" alt="Изображение">
                    <div class="card-body">
                        <h5 class="card-title">${product.boldText}</h5>
                        <p class="card-text">${product.text}</p>
                    </div>
                </a>
            </div>
        </#list>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
</div>
<script src="js/products.js"></script>
</@p.page>
<#import "parts/pageTemplate.ftl" as p>
<@p.page cssFile="css/products.css">


<br>
<div class="container" id="container2">
    <div class="row row-cols-1 row-cols-md-3 g-4;">
        <#list products as product>
            <div class="col">
                <div class="card">
                    <img src="${product.imagePath}" loading="lazy" class="card-img-top" alt="Изображение">
                    <div class="card-body">
                        <h5 class="card-title">${product.boldText}</h5>
                        <p class="card-text">${product.text}</p>
                    </div>
                    <div class="card-footer text-body-secondary">
                        <button type="button" class="btn btn-warning">Warning</button>
                        <form action="/admin/delete/${product.id}" method="post" style="margin: 0;">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <button type="submit" class="btn btn-danger">Удалить</button>
                        </form>
                    </div>
                </div>
            </div>
        </#list>
        <div class="col">
            <div class="card card-add" data-bs-toggle="modal" data-bs-target="#addProductModal">
                <div class="card-body">
                    +
                </div>
            </div>
        </div>
    </div>

    <!-- Модальное окно -->
    <div class="modal fade" id="addProductModal" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <form action="/admin/add" method="post" enctype="multipart/form-data">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="addProductModalLabel">Добавить продукт</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
            </div>
            <div class="modal-body">
              <div class="mb-3">
                <label for="imageFile" class="form-label">Изображение</label>
                <input type="file" class="form-control" id="imageFile" name="imageFile" accept="image/*">
              </div>
              <div class="mb-3">
                <label for="boldText" class="form-label">Заголовок</label>
                <input type="text" class="form-control" id="boldText" name="boldText" required>
              </div>
              <div class="mb-3">
                <label for="text" class="form-label">Описание</label>
                <input type="text" class="form-control" id="text" name="text" required>
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
              <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
          </div>
        </form>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>

</div>
<script src="js/products.js"></script>


</@p.page>
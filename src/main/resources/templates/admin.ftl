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
              <div class="card-footer p-0">
                <div class="row g-0">
                  <div class="col-6 border-end">
                    <form action="/admin/delete/${product.id}" method="post" style="margin: 0; padding: 0;">
                      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                      <button type="button" class="btn btn-outline-danger btn-round-left w-100" onclick="confirmDelete(this.form)">Удалить</button>
                    </form>
                  </div>
                  <div class="col-6">
                    <button class="btn btn-outline-warning btn-round-right w-100"
                        onclick="openEditModal(this)"
                        data-id="${product.id}"
                        data-bold="${product.boldText?js_string}"
                        data-text="${product.text?js_string}"
                        data-company-name="${product.companyName?js_string}"
                        data-page-text="${product.pageText?js_string}"> Редактировать
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </#list>
        <div class="col">
          <div class="card card-add" data-bs-toggle="modal" data-bs-target="#addProductModal">
            <div class="card-body"> + </div>
          </div>
        </div>
      </div>

      <!-- Modal window add -->
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
                  <textarea type="text" class="form-control" id="boldText" name="boldText" rows="4" required></textarea>
                </div>
                <div class="mb-3">
                  <label for="text" class="form-label">Описание</label>
                  <textarea type="text" class="form-control" id="text" name="text" rows="4" required></textarea>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                <button type="submit" class="btn btn-success">Добавить</button>
              </div>
            </div>
          </form>
        </div>
      </div>

      <!-- Modal window edit -->
      <div class="modal fade" id="editProductModal" tabindex="-1" aria-labelledby="editProductModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <form action="/admin/edit" method="post" enctype="multipart/form-data">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="hidden" name="id" id="edit-id">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="editProductModalLabel">Редактировать продукт</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Закрыть"></button>
              </div>
              <div class="modal-body">

                <h3 class="mb-3">Карточка</h3>
                <div class="mb-4">
                  <label for="imageFile" class="form-label text-muted">Фото</label>
                  <input type="file" class="form-control" id="imageFile" name="imageFile" accept="image/*">
                </div>
                <div class="mb-4">
                  <label for="edit-boldText" class="form-label text-muted">Заголовок</label>
                  <textarea class="form-control" id="edit-boldText" name="boldText" rows="2" required></textarea>
                </div>
                <div class="mb-4">
                  <label for="edit-text" class="form-label text-muted">Текст</label>
                  <textarea class="form-control" id="edit-text" name="text" rows="2" required></textarea>
                </div>

                <h3 class="m3-4 mt-4">Страница продукта</h3>
                <div class="mb-4">
                  <label for="edit-companyName" class="form-label text-muted">Название компании или продукта</label>
                  <textarea class="form-control" id="edit-companyName" name="companyName" rows="1" required></textarea>
                </div>
                <div class="mb-4">
                  <label for="edit-pageText" class="form-label text-muted">Развернутый текст</label>
                  <textarea class="form-control" id="edit-pageText" name="pageText" rows="8" required></textarea>
                </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                <button type="submit" class="btn btn-warning">Сохранить</button>
              </div>
            </div>
          </form>
        </div>
      </div>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <script src="js/products.js"></script>
    <script>
      function confirmDelete(form) {
        if (confirm("Вы уверены, что хотите удалить этот продукт?")) {
          form.submit();
        }
      }
    </script>
    <script>
      function openEditModal(button) {

        const id = button.dataset.id;
        const bold = button.dataset.bold;
        const text = button.dataset.text;
        const companyName = button.dataset.companyName;
        const pageText = button.dataset.pageText;

        document.getElementById("edit-id").value = id;
        document.getElementById("edit-boldText").value = bold;
        document.getElementById("edit-text").value = text;
        document.getElementById("edit-companyName").value = companyName;
        document.getElementById("edit-pageText").value = pageText;

        const modal = new bootstrap.Modal(document.getElementById('editProductModal'));
        modal.show();
      }
    </script>
  </@p.page>
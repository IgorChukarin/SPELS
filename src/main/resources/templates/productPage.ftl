<#import "parts/pageTemplate.ftl" as p>
<@p.page cssFile="/css/productPage.css">

  <div class="container mt-5">

    <h1>${product.companyName}</h1>

    <div class="row align-items-center">

      <div class="col-md-6">

<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">

  <div class="carousel-inner">
    <#list product.photos as photo>
      <div class="carousel-item <#if photo?index == 0>active</#if>">
        <img class="d-block w-100" src="${photo}" alt="Slide ${photo?index + 1}">
      </div>
    </#list>
  </div>

  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>


      </div>
      <div class="col-md-6">
        <h3>Описание</h3>
        <div>${product.pageText}</div>
      </div>
    </div>

    <h3>Документация</h3>
    <ul>
      <#list product.documents as doc>
        <li>
          <a href="${doc}" download="${doc}">
            ${doc}
          </a>
        </li>
      </#list>
    </ul>


  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>



</@p.page>
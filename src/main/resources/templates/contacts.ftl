<#import "parts/pageTemplate.ftl" as p>
<@p.page cssFile="css/contacts.css">
<br>
<div class="container" id="container2" style="margin-top: 1%">
    <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-lg-6 mb-4">
                    <div class="card border-0 h-100" style="border-radius: 0;">
                        <div class="flex-column justify-content-center" style="background-color: #f7f5ee;">
                            <div style="display: flex; height: 100%;">
                                <iframe
                                    src="https://yandex.ru/map-widget/v1/?um=constructor%3Ae412cd6606fc78148fdb8a6f71738ec721d999743667a06940c3a64b42a1ff9e&amp;source=constructor"
                                    width="100%" height="415px" frameborder="0"></iframe>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-6 mb-4">
                    <div class="card border-0 h-100">
                        <div class="card-body text-left d-flex align-items-center flex-column"
                            style="background-color: #f7f5ee;">
                            <div class="card-footer" style="background-color: #f7f5ee;">
                                <h3 class=" text-uppercase">свяжитесь с нами</h3>
                                <br>
                                <br>
                                <div class="contact-element">
                                    <p class="mb-1">Телефон (Whats app)</p>
                                    <h5 class="mt-0 color-black">+7 (989) 577-08-83 <button class="copyButton" data-text="+7 (989) 577-08-83"><img src="images/copyicon.png"></button></h5>
                                </div>
                                <div>
                                    <p class="mb-1"><br>Почта</p>
                                    <h5 class="mt-0">
                                    off.spels@gmail.com <button class="copyButton" data-text="off.spels@gmail.com"><img src="images/copyicon.png"></button></h4>
                                </div>
                                <div>
                                    <p class="mb-1"><br>Адрес</p>
                                    <h5 class="mt-0">г. Москва, Кронштадский б-р. 7А, каб. 405В <button class="copyButton" data-text="г. Москва, Кронштадский б-р. 7А, каб. 405В"><img src="images/copyicon.png"></button></h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </section>
    </div>
</div>
<script src="js/contacts.js"></script>
</@p.page>
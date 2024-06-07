const card = document.querySelectorAll('.card');

card.forEach((element, index) => {
    anime({
        targets: element,
        opacity: 1,
        duration: 1500,
        easing: 'easeInOutQuad',
        delay: index * 200,
    });
});
const images = document.querySelectorAll('img');

images.forEach((image) => {
  image.addEventListener('load', () => {
    image.style.opacity = '1';

    const col = document.querySelectorAll('.col');

    col.forEach((element, index) => {
      anime({
        targets: element,
        opacity: 1,
        duration: 1500,
        easing: 'easeInOutQuad',
        delay: index * 200,
      });
    });
  });
});
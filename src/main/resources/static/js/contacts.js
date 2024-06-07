/*
const images = document.querySelectorAll('img');

images.forEach((image) => {
    image.addEventListener('load', () => {
        image.style.opacity = '1';

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
    });
});
*/
const copyButtons = document.querySelectorAll('.copyButton');

    copyButtons.forEach(button => {
        button.addEventListener('click', () => {
            const textToCopy = button.getAttribute('data-text');
            copyTextToClipboard(textToCopy);
            button.classList.add('copied');
            setTimeout(() => {
                button.classList.remove('copied');
            }, 3000);
        });
    });

    function copyTextToClipboard(text) {
        const textarea = document.createElement('textarea');
        textarea.value = text;
        document.body.appendChild(textarea);
        textarea.select();
        document.execCommand('copy');
        document.body.removeChild(textarea);
    }
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
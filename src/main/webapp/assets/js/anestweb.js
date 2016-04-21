(function () {
    'use strict';

    // Inclui a função isEmpty nos elementos INPUT.
    var isEmpty = function () {
        if (this instanceof window.HTMLElement) {
            return (this.value === '');
        } else {
            return false;
        }
    };
    window.HTMLInputElement.prototype.isEmpty = isEmpty;
    window.HTMLTextAreaElement.prototype.isEmpty = isEmpty;

    // Observa o método input para verificar os elementos input
    document.addEventListener('input', function (e) {
        if (e.target instanceof window.HTMLInputElement) {
            if (e.target.isEmpty()) {
                e.target.classList.remove('hasContent');
            } else {
                e.target.classList.add('hasContent');
            }
        }
    }, true);
}());

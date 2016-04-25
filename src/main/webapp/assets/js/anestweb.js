window.addEventListener("load", function () {
    'use strict';

    var elFields = document.querySelectorAll(".field > input, .field > select, .field > textarea");
    for (var el in elFields) {
        if (elFields[el] instanceof window.HTMLElement) {
            elFields[el].addEventListener("focus", function () {
                this.parentElement.classList.add("onfocus");
            });
            elFields[el].addEventListener("blur", function () {
                this.parentElement.classList.remove("onfocus");
            });
        }
    }

    // Máscaras, se presente.
    (function () {
        var fnMasksOnInput = {
            data: function () {
                var value = this.value.replace(/[^0-9]/g, ""),
                       dd = value.substr(0, 2),
                       mm = value.substr(2, 2),
                     yyyy = value.substr(4, 4);

                value = (mm.length > 0) ? dd + "/" : dd;
                value += (yyyy.length > 0) ? mm + "/" : mm;
                value += yyyy;
                this.value = value;
            },
            cpf: function () {
                var value = this.value.replace(/[^0-9]/g, ""),
                       g1 = value.substr(0, 3),
                       g2 = value.substr(3, 3),
                       g3 = value.substr(6, 3),
                      dig = value.substr(9, 2);

                value = (g2.length > 0) ? g1 + "." : g1;
                value += (g3.length > 0) ? g2 + "." : g2;
                value += (dig.length > 0) ? g3 + "-" : g3;
                value += dig;
                this.value = value;
            }
        };
        var elFields = document.querySelectorAll("input[data-mask]");
        for (var el in elFields) {
            if (elFields[el] instanceof window.HTMLInputElement) {
                var mask = elFields[el].getAttribute("data-mask");
                elFields[el].addEventListener("input", fnMasksOnInput[mask], true);
            }
        }
    }());

    // Funcionalidade de remoção de registro, se presente.
    (function () {
        var fnCreateXMLRequest = function () {
            if (typeof XMLHttpRequest !== 'undefined') {
                return new XMLHttpRequest();
            } else {
                var versions = [
                    "MSXML2.XmlHttp.5.0", 
                    "MSXML2.XmlHttp.4.0",
                    "MSXML2.XmlHttp.3.0", 
                    "MSXML2.XmlHttp.2.0",
                    "Microsoft.XmlHttp"
                ];

                for (var v = 0; v < versions.length; v++) {
                    try {
                        return new ActiveXObject(versions[v]);
                    } catch (e){}
                }
            }
        };
        var fnRequestRemover = function (url, onComplete) {
            try {
                var xhr = fnCreateXMLRequest();
                xhr.onreadystatechange = function () {
                    window.console.info(xhr);
                    if (xhr.readyState === 4)  {
                        switch (xhr.status) {
                            case 200:
                                window.location.reload();
                                break;
                            default:
                                var msg = "Não foi possível completar a requisição.";
                                msg += xhr.statusText ? " (" + xhr.statusText + ")" : "";
                                msg += xhr.responseText ? "\n" + xhr.responseText : "";
                                window.alert(msg);
                                if (onComplete) { onComplete(); }
                        }
                    }
                };
                xhr.timeout = 3000;
                xhr.open("DELETE", url, true);
                xhr.send();
            } catch (e) {
                window.alert("A operação não pode ser executada. " + e.message);
            }
        };
        var elLinks = document.querySelectorAll("a[href][data-confirm]");
        for (var el in elLinks) {
            if (elLinks[el] instanceof window.HTMLAnchorElement) {
                elLinks[el].addEventListener("click", function (e) {
                    e.preventDefault();
                    if (window.confirm(this.getAttribute("data-confirm"))) {
                        var _this = this;
                        _this.classList.add("disabled");
                        _this.disabled = true;
                        fnRequestRemover(this.getAttribute("href"), function () {
                            _this.classList.remove("disabled");
                            _this.disabled = false;
                        });

                        _this.blur();
                    }
                }, false);
            }
        }
    }());
});

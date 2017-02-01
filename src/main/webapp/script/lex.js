/**
 * Import stylesheet
 */
if(document.createStyleSheet) {
    document.createStyleSheet('/lex2search/styles/lexportlet.css');
}
else {
    var newCss = document.createElement('link');
    newCss.rel = 'stylesheet';
    newCss.type = 'text/css';
    newCss.href = '/lex2search/styles/lexportlet.css';
    document.getElementsByTagName('head')[0].appendChild(newCss);
}

/**
 * Firefox not showing which checkbox has focus by default
 */
function lexCheckBoxFocus(el) {
    el.parentNode.style.outline = '1px dotted !important';
}

function lexCheckBoxBlur(el) {
    el.parentNode.style.outline = '';
}

function lexTableTitleGeneratorSetTitle(id) {
    var div = document.getElementById(id);
    if(div) {
        var tables = div.getElementsByTagName('table');
        for(var i = 0; i < tables.length; i++) {
            if(tables[i].nodeName == 'TABLE') {
                var tr = tables[i].getElementsByTagName('tr');
                var ths = tr[0].getElementsByTagName('th');
                for(var j = 0; j < ths.length; j++) {
                    if(ths[j].nodeName == 'TH') {
                        var a = ths[j].getElementsByTagName('a');
                        if(a != null) ths[j].title='Sortera p\u00E5 ' + a[0].innerHTML.toLowerCase();
                    }
                }
            }
        }
    }
}

function clearForm(form) {
    var elements=form.getElementsByTagName('input');
    for(var i=0, el; el=elements[i++];) {
        if(el.getAttribute('type') == 'text' && el.name == 'searchText') {
            el.value='';
        }
        else if(el.getAttribute('type') == 'checkbox') {
            el.checked=false;
        }
    }
    elements=form.getElementsByTagName('select');
    for(var i=0; i < elements.length; i++) {
        for(var j=0; j < elements[i].options.length; j++) {
            elements[i].options[j].selected = false;
        }
    }
}

/**
 * Import stylesheet
 */
if(document.createStyleSheet) {
    document.createStyleSheet('/lex2search/styles/lexportletedit.css');
}
else {
    var editCss = document.createElement('link');
    editCss.rel = 'stylesheet';
    editCss.type = 'text/css';
    editCss.href = '/lex2search/styles/lexportletedit.css';
    document.getElementsByTagName('head')[0].appendChild(editCss);
}
require("font-awesome/css/font-awesome.min.css");
require("semantic-ui-css/semantic.min.css");
require("./../../../../src/less/main.less");

window.deps = {
    'react' : require('react'),
    'react-dom' : require('react-dom'),
    'sui' : require('semantic-ui-react')
};

window.React = window.deps['react'];
window.ReactDOM = window.deps['react-dom'];


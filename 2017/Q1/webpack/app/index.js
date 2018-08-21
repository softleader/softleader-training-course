import _ from 'lodash';
import sax from 'sax';
import '../public/css/bootstrap.css'; 

function component () {
  var element = document.createElement('div');

  /* lodash is required for the next line to work */
  element.innerHTML = _.join(['Hello','webpack'], ' ');

  return element;
}

document.body.appendChild(component());

import React from 'react'
import PropTypes from 'prop-types'; // ES6

const Link = (abc) => {
  console.log("abc",abc); //FIXME DEBUG LOG
  if (abc.active) {
        return <span>{abc.children}</span>
    }

    return (
        <a href="#"
           onClick={e => {
               e.preventDefault()
             abc.onClick()
           }}
        >
            {abc.children}
        </a>
    )
}

Link.propTypes = {
    active: PropTypes.bool.isRequired,
    children: PropTypes.node.isRequired,
    onClick: PropTypes.func.isRequired
}

export default Link
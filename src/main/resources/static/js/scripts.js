function createElement(tag_name, attributes, innerHTML)
{
	var element = document.createElement(tag_name);

	setAttributes(element,attributes);

	if ( innerHTML )
	{
		element.innerHTML = innerHTML;
	}

	return element;
}

function setAttributes(element, attributes)
{
	if ( element == null || attributes == null)
		return;

	for (var key in attributes)
	{
	    if (!attributes.hasOwnProperty(key)) continue;

	    var value = attributes[key];

	    if ( typeof value == "function" )
	    	element[key] = value;
	    else
	    	element.setAttribute(key,value);

	}
}
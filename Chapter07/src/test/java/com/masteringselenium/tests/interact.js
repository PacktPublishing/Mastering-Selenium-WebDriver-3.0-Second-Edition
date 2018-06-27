const callback;

window.callback = arguments[arguments.length - 1];

var dataInput = document.createElement('div');
dataInput.id = "se_temp_markup";
dataInput.setAttribute("style", "width: 200px; height: 100px; background-color: yellow; z-index: 99999999; position: fixed; padding: 1rem;");
dataInput.innerHTML = "<p>Enter some text to use in the Selenium test:</p>\n" +
    "<input type=\"text\" id=\"setest_collect_data\">\n" +
    "<button onclick=\"returnDataToSelenium()\">submit</button>";


var script = document.createElement('script');
script.innerHTML = "function returnDataToSelenium() {\n" +
    "    var userInput = document.getElementById('setest_collect_data').value;\n" +
    "    document.getElementById(\"se_temp_markup\").remove();\n" +
    "    window.callback(userInput);\n" +
    "}";

var body = document.getElementsByTagName('body')[0];
body.appendChild(script);
body.appendChild(dataInput);
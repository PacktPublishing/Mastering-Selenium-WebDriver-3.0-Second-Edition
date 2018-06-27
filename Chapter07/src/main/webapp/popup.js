function popupPromise() {


    var element = arguments[0];
    var dataTransfer = {
        data: options.dragData == null ? {} : options.dragData,
        setData: function (eventName, val) {
            if (typeof val === 'string') {
                this.data[eventName] = val;
            }
        },
        getData: function (eventName) {
            return this.data[eventName];
        },
        clearData: function () {
            return this.data = {};
        },
        setDragImage: function (dragElement, x, y) {
        }
    };
    var event = new Event("dragstart");
    event.dataTransfer = dataTransfer;
    event.setData("dragstart", element.id);
    element.dispatchEvent(event);
}

var anonymous = function () {
    console.log('I logged something to the Javascript console');
};
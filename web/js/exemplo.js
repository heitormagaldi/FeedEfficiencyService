/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Exemplo = function() {
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
    this._handlerError = null;
};

Exemplo.Load = function() {
    var _dados = new Exemplo();
    _dados.initialize();
    return _dados;
};

Exemplo.prototype = {
    initialize: function() {
        this._list();

    },
    //chamada ajax
    _list: function() {

        this.executeBind("services/user/list", '', "GET",
                $.createDelegate(this, this._listSuccess),
                $.createDelegate(this, this._erroDados));

    },
    _listSuccess: function(value) {
        alert(value);

        var data = JSON.parse(value);

        if (data.success) {

        }
    },
    executeBind: function(dataUrl, data, type, handlerSuccess, handlerError) {
        this._type = type;
        this._dataUrl = dataUrl;
        this._data = data;
        this._handlerSuccess = handlerSuccess;
        this._handlerError = handlerError;
        this.dataBind();
    },
    dataBind: function() {
        $.ajax({
            type: this._type,
            cache: false,
            data: this._data,
            url: this._dataUrl,
            success: this._handlerSuccess,
            error: this._handlerError
        });
    },
    _erroDados: function(value) {

    }
};
$(document).ready(function() {
    Exemplo.Load();
});
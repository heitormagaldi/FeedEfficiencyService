/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Exemplo = function () {
    this._data = null;
    this._dataUrl = null;
    this._type = null;
    this._dataSource = null;
    this._handlerSuccess = null;
    this._handlerError = null;
};
Exemplo.Load = function () {
    var _dados = new Exemplo();
    _dados.initialize();
    return _dados;
};
Exemplo.prototype = {
    initialize: function () {
        this._list();
    },
    //chamada ajax
    _list: function () {

        this.executeBind("services/refeicao/list", '', "GET",
                $.createDelegate(this, this._listSuccess),
                $.createDelegate(this, this._erroDados));
    },
    _listSuccess: function (value) {
        alert(value);
        var data = JSON.parse(value);

        if (data.success) {
            var table = '<table class="table table-bordered table-hover table-striped">';
            table += '<thead>';
            table += '<tr>';
            table += '<th>Identificador</th>';
            table += '<th>Animal</th>';
            table += '<th>Dura&ccedil;&atilde;o</th>';
            table += '<th>Qtd Consumida</th>';
            table += '<th>Data</th>';
            table += '<th>Hora</th>';
            table += '</tr>';
            table += '</thead>';
            table += '<tbody>';

            for (var i in data.list) {

                table += '<tr class="active">';
                table += '<td>' + data.list[i].id + '</td>';
                table += '<td>' + data.list[i].animal + '</td>';
                table += '<td>' + data.list[i].duracao + '</td>';
                table += '<td>' + data.list[i].consumido + '</td>';
                table += '<td>' + data.list[i].data + '</td>';
                table += '<td>' + data.list[i].hora + '</td>';
                table += '</tr>';
            }
            table += '</tbody>';
            table += '</table>';

            $("#listrefeicao").html(table);
        }
    },
    executeBind: function (dataUrl, data, type, handlerSuccess, handlerError) {
        this._type = type;
        this._dataUrl = dataUrl;
        this._data = data;
        this._handlerSuccess = handlerSuccess;
        this._handlerError = handlerError;
        this.dataBind();
    },
    dataBind: function () {
        $.ajax({
            type: this._type,
            cache: false,
            data: this._data,
            url: this._dataUrl,
            success: this._handlerSuccess,
            error: this._handlerError
        });
    },
    _erroDados: function (value) {
        alert("ERRO");
    }
};
$(document).ready(function () {
    Exemplo.Load();
});



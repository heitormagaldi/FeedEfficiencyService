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

        this.executeBind("services/animal/list", '', "GET",
                $.createDelegate(this, this._listSuccess),
                $.createDelegate(this, this._erroDados));
    },
    _listSuccess: function (value) {

        var data = JSON.parse(value);
        
        if (data.success) {
            var table = '<table class="table table-bordered table-hover table-striped">';
            table += '<thead>';
            table += '<tr>';
            //table += '<th>Codigo</th>';
            table += '<th>Registro</th>';
            table += '<th>Manejo</th>';
            //table += '<th>Ra&ccedil;a</th>';
            table += '<th>Nascimento</th>';
            table += '<th>Nome</th>';
            table += '<th>Tipo</th>';
            table += '<th>Editar</th>';
            table += '<th>Remover</th>';
            

            table += '</tr>';
            table += '</thead>';
            table += '<tbody>';

            for (var i in data.list) {
                if (data.list[i].manejo == '')
                    table += '<tr class="danger">';
                else {
                    if (data.list[i].registro == '') {
                        table += '<tr class="warning">';
                    } else
                    {
                        table += '<tr class="active">';
                    }
                }
                //table += '<td>' + data.list[i].codigo + '</td>';
                table += '<td>' + data.list[i].registro + '</td>';
                table += '<td>' + data.list[i].manejo + '</td>';
                //table += '<td>' + data.list[i].raca + '</td>';
                table += '<td>' + data.list[i].nascimento + '</td>';
                table += '<td>' + data.list[i].nome + '</td>';
                
                if (data.list[i].sexo == 'F') {
                    table += '<td>Novilha</td>';
                }else{
                    table += '<td>Touro</td>';
                }
                
                table += '<td><a href="formAnimal.jsp?codigo='+data.list[i].codigo+'"><i class="fa fa-fw fa fa-pencil-square-o"></i></a></td>';
                table += '<td><a href="/forms.html&teste"><i class="fa fa-fw fa-times"></i></a></td>';
                table += '</tr>';
            }
            table += '</tbody>';
            table += '</table>';
            
            $("#listanimais").html(table);
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



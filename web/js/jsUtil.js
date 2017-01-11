/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

jQuery.createDelegate = function(instance, method) {
    return function() {
        return method.apply(instance, arguments);
    }
};

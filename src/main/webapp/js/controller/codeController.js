//定义处理器
app.controller("codeController", function ($scope, codeService) {
    $scope.operationList = ["insert", "delete", "update", "select"];

    $scope.init = function () {
        codeService.initDatabase($scope.database).success(function (response) {
            $scope.fieldNames = response;
        });
    };

    $scope.send = function () {
        $scope.entity.tableName = $scope.database.tableName;
        $scope.entity.fieldList = $scope.fieldArray.toString();
        $scope.entity.conditionList = $scope.conditionArray.toString();

        switch ($scope.entity.operation) {
            case "insert":
                codeService.insert($scope.entity);
                break;
            case "delete":
                codeService.delete($scope.entity);
                break;
            case "update":
                codeService.update($scope.entity);
                break;
            case "select":
                codeService.select($scope.entity);
                break;
        }
    };

    // 查询的字段列表
    $scope.fieldArray = [];
    $scope.updateFieldList = function ($event, fieldName) {
        fieldName = " " + fieldName;
        if($event.target.checked){
            //选中
            $scope.fieldArray.push(fieldName);
        } else {
            //反选，则需要从选中集合中删除
            var index = $scope.fieldArray.indexOf(fieldName);

            //参数1：删除元素对应索引号，参数2：删除的个数
            $scope.fieldArray.splice(index, 1);
        }
    };

    // where条件的字段列表
    $scope.conditionArray = [];
    $scope.updateConditionList = function ($event, conditionName) {
        if($event.target.checked){
            //选中
            $scope.conditionArray.push(conditionName);
        } else {
            //反选，则需要从选中集合中删除
            var index = $scope.conditionArray.indexOf(conditionName);

            //参数1：删除元素对应索引号，参数2：删除的个数
            $scope.conditionArray.splice(index, 1);
        }
    }
});
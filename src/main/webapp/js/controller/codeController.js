//定义处理器
app.controller("codeController", function ($scope, codeService) {
    $scope.operationList = ["insert", "delete", "update", "select"];

    $scope.send = function () {
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
    }
});
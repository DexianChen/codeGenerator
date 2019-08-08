//定义service
app.service("codeService", function ($http) {
    this.initDatabase = function (database) {
        return $http.post("http://localhost:8080/initDatabase", database);
    };

    this.insert = function (entity) {
        return $http.post("http://localhost:8080/insert", entity);
    };
    this.delete = function (entity) {
        return $http.post("http://localhost:8080/delete", entity);
    };
    this.update = function (entity) {
        return $http.post("http://localhost:8080/update", entity);
    };
    this.select = function (entity) {
        return $http.post("http://localhost:8080/select", entity);
    };
    this.resultMap = function (fieldNameList, fieldTypeList, filePath) {
        return $http.get("http://localhost:8080/resultMap?fieldNameList=" + JSON.stringify(fieldNameList)
            + "&fieldTypeList=" + JSON.stringify(fieldTypeList) + "&filePath=" + filePath);
    };
});
//定义service
app.service("codeService", function ($http) {
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
});
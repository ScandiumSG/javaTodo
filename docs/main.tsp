import "@typespec/http";
import "@typespec/rest";
import "@typespec/openapi3";

using TypeSpec.Http;
using TypeSpec.Rest;

@service({
    title: "Todo List API"
})
@server("http://localhost:8080/", "Local docker endpoint")
namespace TodoList;


@route("/task/v2/")
namespace Tasks {
    @get op getAll(
        @header accept: string): {
            @statusCode statusCode: 200; 
            @body tasks: Task[];
        };
}

model Task {
    id: int64;
    title: string;
    description: string,
    completed: boolean;
    createdAt: utcDateTime;
    updatedAt: utcDateTime;
}
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


@tag("TodoList v2")
namespace Tasks {
    @route("/task/v2/")
    @summary("GET ALL")
    @doc("Retrieve all tasks")
    @get op RetrieveAll(): {
            @statusCode statusCode: 200; 
            @body tasks: pureModels.Task[];
        } | {
            @statusCode statusCode: 204;
            @body "No content": DTOModels.noContent[];
        };

    @route("/task/v2/{id}")
    @summary("GET")
    @doc("Retrieve all tasks")
    @get op Retrieve(
        @path id: integer;
    ): {
            @statusCode statusCode: 200; 
            @body tasks: pureModels.Task;
        } | {
            @statusCode statusCode: 404;
            @body "Not found": errors.notFound;
        };

    @route("/task/v2/")
    @summary("POST")
    @doc("Create a new task")
    @post op Create(
        @body PostTask: DTOModels.PostTask): {
            @statusCode statusCode: 201;
            @body task: pureModels.Task;
        }| {
            @statusCode statusCode: 403;
            @body "Bad request": errors.badRequest;
        };

    @route("/task/v2/")
    @summary("PUT")
    @doc("Update a task value")
    @put op Update(
        @body PostTask: pureModels.Task): {
            @statusCode statusCode: 201;
            @body task: pureModels.Task;
        } | {
            @statusCode statusCode: 404,
            @body "Not found": errors.notFound;
        };

    @route("/task/v2/{id}")
    @summary("DELETE")
    @doc("Delete a task with the provided id")
    @delete op Delete(
        @path id: integer;
    ): {
        @statusCode statusCode: 200;
        @body nothing: {};
    };
}



namespace pureModels{
    model Task {
        id: int64;
        title: string;
        description: string,
        completed: boolean;
        createdAt: utcDateTime;
        updatedAt: utcDateTime;
    }


}

namespace DTOModels {
    model PostTask {
        title: string;
        description: string,
    }

    model noContent {

    }
}

namespace errors {
    @error
    model notFound {
        code: 404,
        message: "Not found",
    }

    @error
    model badRequest {
        code: 403,
        message: "Bad request",
    }
}
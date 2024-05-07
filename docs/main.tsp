import "@typespec/http";
import "@typespec/rest";
import "@typespec/openapi3";

using TypeSpec.Http;

model Task {
    id: integer;
    title: string;
    description: string,
    completed: boolean;
    createdAt: string;
    updatedAt: string;
}

@route("/task/v2/")
interface Tasks {
    list(): Task[];
}
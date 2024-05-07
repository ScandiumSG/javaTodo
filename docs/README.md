# About

This folder contains api documentation, contained within the `api-specs.html` file. 

API is created using [TypeSpec](https://typespec.io/), which generates a openapi.yaml file which is then converted to a [Redocly](https://redocly.com/) html spec sheet. 

The API itself is defined the the `main.tsp` file.

## How to build

To build or update this api documentation simply run the node command: 
```
npm run generate
```
<details>

The generate command will first install the project via `npm install`, then generate the openapi.yaml file, by doing the command 
```
tsp compile .
```

The command will then execute the redocly build command:
```
redocly build-docs ./tsp-output/@typespec/openapi3/openapi.yaml -o api-specs.html
```
</details>
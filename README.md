# Nimap-Machine-Test
Build a Spring Boot REST API to manage Categories and their associated Products as part of the Nimap Machine Test

Functional Requirements:
  Entity Relationship:

    -Establish a One-to-Many relationship between Category and Product:
        -One Category can have multiple Products.
        -Each Product belongs to one Category.

  CRUD Operations:
    -Implement Create, Read, Update, and Delete (CRUD) for:
      -Category
      -Product
      
  Server-Side Pagination:
    -Implement pagination for fetching products.
    -Accept page and size as query parameters.

  Fetch Category with Products:
    -Provide an endpoint to retrieve category details along with its products.

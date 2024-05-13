# Test Task
### Simple API for operations with Entity USER.

## Main information.
This API created for interacting with Enity `User` and some different ways.
All information, that given in request validates by server and returns right responces.
Server able to:
- create new user.
- update new user.
- get all of users.
- update all of users.
- get user by his unique ID.
- update user by his unique ID.
- get all users in range of date by parameter birth date
- delete user by his unique ID.

---
## Endpoints
- **GET** |  `.../users/all` returns an object, with all users, created before or Exception.
- **GET** |  `.../users/{id}` returns an object, of user, which parameter `id` that equals to request parameter number or Exception.
- **POST** |  `.../users/new` returns **OK** status of new saved user or Exception.
- **POST** |  `.../users/update/{id}` returns **OK** status of updating information about user, which param `id` that equals to request param number or return Exception.
- **POST** |  `.../users/update/all` return **OK** status of updating information to all records in Database or Exception.
- **DELETE** | `.../users/{id}` returns **OK** status of deleting record in Database with parameter `id` that equals to request param number or return Exception.
- **GET** | `.../users/search?YYY-MM-DD;YY-MM-DD` returns an object of users that parameter `birthDate` equals range of date from request or return Exception.
---
Now its `public` repo with public code and you can borrow some parts for your educational purposes.

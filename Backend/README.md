# E-lang API Documentation
## How To Use

### Note
```
Request -> url
Requirement -> Data that needed and send to API
Response -> Api Response

String -> use "" -> nama:"this is a name"
Long -> number only ->id:13
file -> upload a file
```

---
## users
---

### Register Akun

Request `POST /user/register`

Requirement 
```
Body
user:{ "nama":String, "password":String, "email":String, "nomorTelpon":String , "alamat":String }
image:file
```

Response OK :
```
{
    "id": Long,
    "password": String,
    "nama": String,
    "email": String,
    "nomorTelpon": String,
    "alamat": String,
    "verified": String,
    "imageUrl": String,
    "created_at": Date,
    "updated_at": Date,
    "adminId": Long
}
```
Response Bad Request : Email already used


### Login

Request `POST /user/login`

Requirement 
```
Body
email:String
password:String
```

Response OK :
```
{
    "id": Long,
    "password": String,
    "nama": String,
    "email": String,
    "nomorTelpon": String,
    "alamat": String,
    "verified": String,
    "imageUrl": String,
    "created_at": Date,
    "updated_at": Date,
    "adminId": Long
}
```
Response Not Found : Email and Password not found

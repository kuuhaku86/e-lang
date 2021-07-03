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
Date -> date format, also use "" -> "2023-03-13T17:59:39.000+00:00"
List<String> -> 0 or more string, use [] -> [ "String 1", "String 2" ]
null -> empty, in response -> "Attribute":null
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

### Update Akun

Request `POST /user/update`

Requirement 
```
Body
user:{ "id":Long, "nama":String, "password":String, "email":String, "nomorTelpon":String , "alamat":String }
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
    "created_at": NULL,
    "updated_at": Date,
    "adminId": Long
}
```
Response Not Found : User ID not found

---
## Barang
---

### Buat Barang/Pengajuan Baru

Request `POST /barang/create`

Requirement 
```
Body
barang:{ "nama":String,"hargaAwal":Long, "deskripsi":String, "lelangStart":Date, "lelangFinished":Date, "userId":Long }
image:file
kategoriList:List<String>
```

Response OK :
```
```
Response 

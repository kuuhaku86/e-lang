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
List<Object> -> 0 or more object, use [] -> [ object 1, object 2 ] or [ { "atr1":value, "atr2", ... }, {...}, ...] 
null -> empty, in response -> "Attribute":null
```
```
Object User :
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
```
```
Object Barang :
    "id": Long,
    "nama": String,
    "hargaAwal": Long,
    "deskripsi": String,
    "status": String,
    "lelangStart": Date,
    "lelangFinished": Date,
    "userId": Long,
    "imageUrl": String,
    "created_at": Date,
    "updated_at": Date,
    "kategoriList": List<Kategori>,
    "penawaranBarangList": List<PenawaranBarang>
```
```
Object Kategori :
    "id": Long,
    "nama": String
```
```
Object Penawaran Barang :
    "id": Long,
    "harga": Long,
    "userId": Long,
    "created_at": Date,
    "updated_at": Date
```
```
Object Pembayaran :
    "id": Long,
    "status": String,
    "buktiPembayaran": String,
    "userId": Long,
    "adminId": Long,
    "deadline": Date,
    "penawaranId": Long,
    "created_at": Date,
    "updated_at": Date
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

Response OK : ` Object User `

Response Bad Request : Email already used


### Login

Request `POST /user/login`

Requirement 
```
Body
    email:String
    password:String
```

Response OK : ` Object User `

Response Not Found : Email and Password not found


### Update Akun

Request `POST /user/update`

Requirement 
```
Body
    user:{ "id":Long, "nama":String, "password":String, "email":String, "nomorTelpon":String , "alamat":String }
    image:file
```

Response OK : ` Object User `

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

Response OK : `Object Barang`

Response Not Found : User ID not exist


### Update Barang/Pengajuan

Request `POST /barang/update`

Requirement 
```
Body
    barang:{ "nama":String,"hargaAwal":Long, "deskripsi":String, "lelangStart":Date, "lelangFinished":Date, "userId":Long, "id":Long, "status":String }
    image:file
    kategoriList:List<String>
```

Response OK : `Object Barang`

Response Not Found : User ID not exist

Response Bad Request : Barang has been process


### View Barang/Pengajuan

Request `GET /barang/view`

Requirement 
```
Body
    barangId:Long
```

Response OK : `Object Barang`

Response Not Found : Barang Id not exist


### View My Barang/Pengajuan

Request `GET /barang/mine`

Requirement 
```
Body
    userId:Long
```

Response OK : ` List<Barang> `

Response Not Found : User ID not exist

Response Bad Request : Barang has been process



---
## Pelelangan
---
### View Pelelangan

Request `Get /pelelangan/`

No Requirement 

Response OK : `List<Barang>`


### Bid Pelelangan

Request `POST /pelelangan/bid`

Requirement :
```
Body
    harga:Long
    userId:Long
    barangId:Long
```

Response OK : `Object Penawaran Barang`

Response Bad Request : Barang have not processed

Response Not Found : Barang not Exist


### View My Bid

Request `Get /pelelangan/bidku`

Requirement :
```
Body
    userId:Long
```

Response OK : `List<Barang>`

Response Not Found : User not Exist



---
## Pembayaran
---
### Ajukan Pembayaran

Request `POST /pembayaran/ajukan`

Requirement :
```
pembayaran:{ "userId":Long, "penawaranId":Long }
file:file
```

Response OK : `Object Pembayaran`


### View My Pembayaran

Request `GET /pelelangan/mine`

Requirement :
```
Body
    userId:Long
```

Response OK : `List<Pembayaran>`

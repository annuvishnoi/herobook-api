# Herobook REST API
## API Specifications
**Resource Summary**
| URI                                                | HTTP Method |   HTTP Status   | Description                                           |
|----------------------------------------------------|-------------|-----------------|-------------------------------------------------------|
| herobook.com/api/heroes?role={roleName}             | GET         |   200 OK		 | Return the list of name of heroes when role is visitor   |
| herobook.com/api/heroes/{heroName}?role={roleName}   | GET         |   200 OK        | Return the details for that hero                    |
| herobook.com/api/heroes/{heroName}?role={roleName}    | GET         |   400 BAD REQUEST  | Returns error message when hero is not found         |
| herobook.com/api/villains?role={roleName}               | GET         |   200 OK		 | Return the list of name of villains when role is visitor   |
| herobook.com/api/villains/{villainName}?role={roleName} | GET         |   200 OK   | Return the details for that villains                   |
| herobook.com/api/villains/{villainName}?role={roleName} | GET       |   400 BAD REQUEST | Returns error message when villains is not found   |
| herobook.com/api/favorites?role={roleName} | POST       |   201 CREATED | Creates an empty favorites list   |
| herobook.com/api/favorites/{favoritesId}/heroes/{heroName}?role={roleName} | PUT       |   200 OK | Returns updated favorites list  |
| herobook.com/api/favorites/{favoritesId}?role={roleName} | GET      |   200 OK | Returns requested favorites list  |
| herobook.com/api/favorites/{favoritesId}/heroes/{heroName}?role={roleName} | DELETE       |   200 OK | Returns updated favorites list  |

---
**GET /api/heroes?role={roleName}**

Response Body:
```json
{
	"data":["Super Man", "Shaktiman", "Spider Man"],
	"errorMessages":[
		"errors",
		"errors"
	]		
}
```

---

**GET /api/heroes/{heroName}**

Response Body:
```json
{
	"data":{
		"image" : "string",
		"realName" : "string",
		"heroName" : "string",
		"height" : "string",
		"weight" : "string",
		"specialPower" : "string",
		"intelligence" : "string",
		"strength" : "string",
		"power" : "string",
		"speed" : "string",
		"agility" : "string",
		"description" : "string",
		"story" : "string"
	},
	"errorMessages":[
		"Hero not found",
		"errors"
	]	
}
```

---

**GET /api/villains?role={roleName}**

Response Body:
```json
{
	"data":["Green Goblin", "Kilvish", "Thanos"],
	"errorMessages":[
		"errors",
		"errors"
	]		
}
```

---

**GET /api/villains/{villainName}**

Response Body:
```json
{
	"data":{
		"image" : "string",
		"realName" : "string",
		"villainName" : "string",
		"archRival" : "string",
		"height" : "string",
		"weight" : "string",
		"specialPower" : "string",
		"intelligence" : "string",
		"strength" : "string",
		"power" : "string",
		"speed" : "string",
		"agility" : "string",
		"description" : "string",
		"story" : "string"
	},
	"errorMessages":[
		"Villain not found",
		"errors"
	]	
}
```
#### To Be Implemented

---

**POST /api/favorites?role={roleName}**

Validation: 
 - Roles allowed to create favorites 
   - Fan

Response Body:
```json
{
	"data":{
		"favoritesId":"id",
		"favorites" :[]
	},
	"errorMessages":[
		"errors",
		"errors"
	]		
}
```

---

**PUT /api/favorites/{favoritesId}/heroes/{heroName}?role={roleName}**

Validation: 
 - Roles allowed to create favorites 
   - Fan

Response Body:
```json
{
	"data":{
		"favoritesId":"id",
		"favorites" :["heroName"]
	},
	"errorMessages":[
		"errors",
		"errors"
	]		
}
```

---

**GET /api/favorites/{favoritesId}?role={roleName}**

Validation: 
 - Roles allowed to create favorites 
   - Fan

Response Body:
```json
{
	"data":{
		"favoritesId":"id",
		"favorites" :["Superman","Batman"]
	},
	"errorMessages":[
		"errors",
		"errors"
	]		
}
```

---

**DELETE /api/favorites/{favoritesId}/heroes/{heroName}?role={roleName}**

Validation: 
 - Roles allowed to create favorites 
   - Fan

Response Body:
```json
{
	"data":{
		"favoritesId":"id",
		"favorites" :["Batman"]
	},
	"errorMessages":[
		"errors",
		"errors"
	]		
}
```
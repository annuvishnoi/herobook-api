# Herobook REST API
## API Specifications
**Resource Summary**
| URI                                                | HTTP Method |   HTTP Status   | Description                                           |
|----------------------------------------------------|-------------|-----------------|-------------------------------------------------------|
| herobook.com/api/heroes?role={roleName}              		     	 | GET         |   200 OK		 | Return the list of name of heroes when role is visitor               |
| herobook.com/api/heroes/{heroName}?role={roleName}                  | GET         |   200 OK        | Return the details for that hero                           |


---
**GET /api/heroes?role={roleName}**

Response Body:
```json
{
	"data":["Super Man", "Shaktiman", "Spider Man"]	
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
		"errors",
		"errors"
	]	
}
```

---

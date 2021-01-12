# Herobook REST API
## API Specifications
**Resource Summary**
| URI                                                | HTTP Method |   HTTP Status   | Description                                           |
|----------------------------------------------------|-------------|-----------------|-------------------------------------------------------|
| herobook.com/api/heroes?role={rolaName}              		     	 | GET         |   200 OK		 | Return the list of name of heroes when role is visitor               |
| domain.com/api/heroes/{heroName}                         | GET         |   200 OK        | Return the details for that hero                           |


---
** GET /api/heroes?role={rolaName}  **
Response Body:
```json
["Super Man", "Shaktiman", "Spider Man"]
]
```
# president-election backend
Simplified president election backend service 

# requirements
1.	Implement an endpoint that returns a list of candidates available: name, number on the list and short summary of their agenda
2.	Implement an endpoint that enables voting for a candidate

Implement endpoints that return voting result reports: 
1.	Overall distribution of votes amongst candidates
2.	Voting result distribution amongst different regions
3.	The winner endpoint. It must return a single candidate if he/she was voted for by more than 50%. Otherwise it must return two most voted candidates

## API Usage

### Base URL:
http://localhost:8080/api/elections/

### List all candidates

**Definition**

`GET /candidates`

**Response**
- `200  OK` on success

```json
    {
        "fullName": "Donald Trump",
        "number": 1,
        "agenda": "I will make america great again!"
    },
    {
        "fullName": "Rosie Odonel",
        "number": 2,
        "agenda": "Im gonna make some pankakes!"
    },
    {
        "fullName": "Eric Cartman",
        "number": 3,
        "agenda": "Cats are so cewl, bad mister kitty!"
    },
```

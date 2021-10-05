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
### Register a new vote

**Definition**
`POST /vote`

**Arguments**

- `"candidateNumber":integer` a candidate's number on the list of the voted candidate
- `"voterSsn":integer` a voter's social security number 

**Response**
- `201 Created` on success 

If a vote has already been cast by the voter, then error message will be returned. 

- `401 Unauthorised` on trying to vote again

```json
{
    "errorMessage": "A vote has already been cast by the voter 122"
}
```

- `404 Not Found` if the candidate does not exist

```json
{
    "errorMessage": "Candidate does not exist 143"
}
```

- `404 Not Found` if the voter is not registered

```json
{
    "errorMessage": "Voter does not exist 234234"
}
```
## Lookup of overall distribution of votes amongst candidates

**Definition**
`GET /votes`

**Response**

- `200 OK` on success

```json
    {
        "candidate": {
            "fullName": "Donald Trump",
            "number": 1,
            "agenda": "I will make america great again!"
        },
        "numberOfVotes": 3
    },
    {
        "candidate": {
            "fullName": "Rosie Odonel",
            "number": 2,
            "agenda": "Im gonna make some pankakes!"
        },
        "numberOfVotes": 2
    },
    {
        "candidate": {
            "fullName": "Eric Cartman",
            "number": 3,
            "agenda": "Cats are so cewl, bad mister kitty!"
        },
        "numberOfVotes": 0
    },
```

## Lookup of voting result distribution amongst different regions

**Definition**
`GET /regions`

**Response**

- `200 OK` on success

```json
    {
        "region": "Mid-Atlantic",
        "votes": [
            {
                "candidate": {
                    "fullName": "Rosie Odonel",
                    "number": 2,
                    "agenda": "Im gonna make some pankakes!"
                },
                "numberOfVotes": 1
            }
        ]
    },
    {
        "region": "New England",
        "votes": [
            {
                "candidate": {
                    "fullName": "Donald Trump",
                    "number": 1,
                    "agenda": "I will make america great again!"
                },
                "numberOfVotes": 1
            }
        ]
    },
    {
        "region": "The South",
        "votes": [
            {
                "candidate": {
                    "fullName": "Donald Trump",
                    "number": 1,
                    "agenda": "I will make america great again!"
                },
                "numberOfVotes": 2
            }
        ]
    },
```
## Return a winner cadidate

**Definition**
`GET /winner`

**Response**

- `200 OK` on success when candidates been voted for by more than 50%

```json
    {
        "fullName": "Donald Trump",
        "number": 1,
        "agenda": "I will make america great again!"
    }
```
- `200 OK` on success when there is no one winner candidate

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
    }
```
- `404 Not Found` when there are more than two candidates with the same votes

```json
{
    "errorMessage": "To many participants (3) have the same amount of votes. No clear winner."
}
``` 

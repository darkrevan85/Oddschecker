# Intro
The most important aspect of providing an odds comparison site is data. Oddschecker aggregates odds from over 100+ bookmakers worldwide.

# Task

Following the Swagger specification included with this task, we want you to build a new service that allows our users to 
offer odds to other users. In essence, any user can become a bookmaker.

In order to achieve this, we want to build a new system that:

- Allows any user to submit their odds for an ID (as long as they are valid)
- Returns all of the odds for a given ID when requested

# Acceptance Criteria 

## Scenario: Handle Valid Odds
- _Given_ I am a user
- _When_ I submit valid odds for an ID

`1/10`
`2/1`
`SP`

- _Then_ my odds are accepted

## Scenario: Retrieve Odds
- _Given_ I am a user
- _When_ I request to see odds for an ID 
- _Then_ I can see all of the odds for that ID

## Scenario: Handle Invalid Odds
- _Given_ I am a user
- _When_ I submit invalid odds for an ID:

`0/1` `SP/1` `2/3/4` `23*4`

- _Then_ my odds are rejected

# What is expected
You have freedom in how you implement the solution, but a few things should appear in the final submission:
- Your solution should run on a single machine.
- Your solution should be provided in Java
- Include instructions on how to run the solution
- Include tests

# What isn't expected
- You do not need to implement any authentication. A user can be represented in any way.
- You do not need a user interface

# Submitting your test
- Please submit a zip file with your full project.

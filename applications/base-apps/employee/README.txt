This folder contains implementations of the employee register. 

The following actions should be possible against an implementation of the employee register.
Create employee - creates an employee with the provided UUID as id.
Update employee - updates an employee with the provided UUID as id.
Delete employee - deletes an employee with the provided UUID as id.
Get employee - returns the current information about an employee.
Get changes - returns a list of employee-id and its modification date.

Example json for an employee
{
 "id" = "an uuid",
 "firstname" = "John",
 "lastname" = "Doe"
 }
 
Example json for the updated list
{
 [
  { "employee-id" = "an uuid",
    "changedate" = "a date" }
  { "employee-id" = "another uuid",
    "changedate" = another date" }
 ]
}

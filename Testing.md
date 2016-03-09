Code Acceptance Requirements
====================

Background
---------------------
Automated tests provide consistent and reliable protection against unintentional regressions, fand make it possible for developers to confidently release frequent updates to [redacted]. Without effective testing, system behavior will be unstable. Likewise, addressing technical debt and maintaining code quality ensure that code written once can be easily understood when it is subsequently read dozens of times in order to debug an issue or build new functionality. Relatively small changes can make a big difference to ensure applications are bug-free, solidly-built, and out in production on time.

Because automated test coverage and code quality is inconsistent across projects and the definition of "deployable code" varies widely, we strongly recommend the adoption of [redacted]-wide code-acceptance standards for testing and code quality written in collaboration with teams' technical leads, communicated via [redacted], and enforced via the CM team.

This document serves as the draft of an [redacted]-wide code quality guideline. This is a Work-In-Progress document and we will amend it as we progress.

Code quality is a set of metrics with the purpose of boosting confidence in the maintainability of the code and easing rapid adaptation to changing priorities in an agile software development environment.

Code quality is directly correlated to the availability and comprehensiveness of:
*	Automated unit tests
*	Automated integration tests
*	Adoption of standard code writing rules
*	Static code analysis
*	One step builds
*	One step deployments
*	Performance testing

It can be measured using widely available open source tools such as [redacted], [redacted] and [redacted]. These tools are already installed in the [redacted] environment.

Recommendations
---------------------

To help with the adoption of team wide standards and code robustness principles, as first steps, we recommend:

* Testing
  * Have mandatory training sessions where key testing concepts are reviewed in order to help engineers understand why testing is important, the difference between different types of tests, and how to properly implement each.
  * Ensure that **each project has unit tests and integration tests** (and where applicable, acceptance tests).
    *	Unit tests to target a unit of code (such as method, class) where internal dependencies are mocked.
    *	Integration tests, broader in scope, to target a behavior across modules of a project. External dependencies are either injected or mocked.
    *	Achieve coverage of: 20% after [redacted], 50% after [redacted], 80% after [redacted].
  * Deployments should be blocked if they fail acceptance testing, as they do not fulfill **the definition of deployable code**.
  * **Unless approved by [redacted], components with failing tests must not be deployed**.
* Code Quality
  *	**Fixing all code issues with a severity of high or above** as reported from [redacted] 
  *	Require all projects to include a top level README.md file documenting the necessary build steps needed to compile and locally deploy the project.
  *	All middle layer teams to provide a short specification documentation on how clients interact with their project's endpoints.
  *	Encourage engineers to **comment their code** and move documentation necessary for understanding the code into the code itself.
  *	Standardize common code into separate repositories and common libraries.


These recommendations can be supplemented with others to make the code more readable and maintainable. We are aware that teams are bound by business commitments, but we believe the aforementioned recommendations will provide the best value for the level of effort required to exercise them.

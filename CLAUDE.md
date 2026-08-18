## 1. Technical Project Context

You are contributing to the development of a platform that lets readers submit a text for AI analysis, have it segmented into scenes based on tone (dialogue, exposition, action, tension...), and get each scene matched with a royalty-free audio track. The output is exported into a proprietary format, readable via a dedicated app on e-readers or phones, backed by a community library of previously generated tracks.

Refer to the `project-scoping-document.md` for the full functional context (target users, MVP scope, constraints, risks). Every technical decision must stay aligned with that scoping document; if a request departs from it, flag the discrepancy before writing any code.

## 2. Tech Stack

- **Backend:** Java (latest stable LTS version, unless stated otherwise), Spring Boot
- **Frontend:** Angular (latest stable version), strict TypeScript
- **Database:** to be confirmed based on need (relational by default for metadata; consider object storage for audio/text files)
- **Testing:** JUnit 5 + Mockito on the backend, Jasmine/Karma or Jest on the frontend
- **Build:** Maven or Gradle (specify which one in the first exchange and stick with it)

Never silently switch tools, major versions, or structural libraries (test framework, ORM, Angular state management) without explicitly flagging it and explaining why.

## 3. Code Quality Principles (non-negotiable)

- **Readability first:** explicit naming, short single-responsibility functions, no avoidable duplication (DRY)
- **Follow language conventions:** standard Java conventions (Effective Java) on the backend, official Angular style guide on the frontend
- **Clean architecture:** clear separation of layers (controller / service / repository on the Java side; component / service / store on the Angular side). No business logic in controllers or components.
- **Explicit error handling:** no silently swallowed exceptions, no unjustified `any` in TypeScript, no empty `catch` blocks
- **Baseline security:** validate user input (text uploads in particular), no hardcoded secrets, proper CORS and authentication handling
- **Useful comments, not noise:** comment the "why," not the "what," when the code is already clear

## 4. Certainty That the Code Works — Mandatory Protocol

Before delivering any response containing code, systematically apply this protocol:

1. **Mental compile/execution, line by line** — check for syntax errors, type errors, missing imports, or calls to methods that don't exist in the API being used
2. **Actual execution whenever possible** — if the environment allows it, actually run the code (build, tests, launch the application) rather than relying on a mere read-through. Never claim a test passes without having actually run it and seen it pass.
3. **Corresponding tests** — for any new feature or bug fix, produce an associated test that fails before the fix and passes after
4. **Edge cases made explicit** — list at least the relevant edge cases (empty input, very long text, no network, missing audio track, etc.) and state how the code handles them
5. **No unverified claims** — never write "this should work" without having verified it. If a check couldn't be performed (e.g., an external dependency unavailable in the environment), say so explicitly rather than letting it appear validated.

## 5. Mandatory Code Audit Before Any Substantial Response

Before delivering a response with new or substantially modified code, perform a self-audit and report it briefly (as a short checklist or a short paragraph) either right before or right after the code, whichever reads better:

- [ ] Does the code meet exactly the stated goal, no more and no less (no unrequested over-engineering)?
- [ ] Is it consistent with the existing project architecture (no contradictory pattern introduced without justification)?
- [ ] Are obvious security flaws ruled out (injection, unvalidated data, exposed secrets)?
- [ ] Is performance reasonable for the context (no obviously costly loop or query without necessity)?
- [ ] Are tests provided and run successfully?
- [ ] Are added dependencies justified and minimal?

If the audit surfaces an issue, fix it before presenting the response rather than delivering code known to be flawed.

## 6. Expected Response Format

- Respond in English, unless requested otherwise
- For any non-trivial task: a brief summary of the approach before the code, then the code, then the audit checklist (or the reverse order if more readable), then any remaining concerns/uncertainties if applicable
- When a functional requirement is ambiguous, ask rather than silently guessing — unless the reasonable assumption is minor, in which case state it explicitly and proceed
- Never present unverified code as final; clearly distinguish "verified and working" from "to be validated"

## 7. What Must Never Happen

- Never deliver untested code while presenting it as production-ready
- Never introduce a dependency, framework, or major architectural change without flagging it and explaining the reason
- Never ignore a constraint from the scoping document (public-domain/non-proprietary texts only, AI analysis quotas, etc.) without explicitly mentioning it
- Never mask a technical uncertainty behind reassuring language

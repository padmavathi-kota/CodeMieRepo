---
name: develpment-cs
description: An engineering-focused assistant that reads enhancement request design documents from the “DesignData” source and reviews project files in the workspace to understand the system’s intent, architecture, tech stack, frameworks, and coding standards. It then translates the design into an actionable implementation plan and provides tailored code snippets and integration guidance that fit the existing project structure and conventions.
---

# Develpment-CS

An engineering-focused assistant that reads enhancement request design documents from the “DesignData” source and reviews project files in the workspace to understand the system’s intent, architecture, tech stack, frameworks, and coding standards. It then translates the design into an actionable implementation plan and provides tailored code snippets and integration guidance that fit the existing project structure and conventions.

## Instructions

Use this skill when the user asks to consult the Develpment-CS assistant.

1. **Mint a workflow id once at the start of every task that calls this assistant.** Reuse it for every invocation in that task. Suggested shell pattern: `workflow_id="develpment-cs-$(date +%Y%m%d-%H%M%S)-$$"`.
2. **Pass it as `--conversation-id` on every call** so the assistant has a clean, per-task server-side context. Do not rely on the implicit `CODEMIE_SESSION_ID` env-var fallback — that id is shared across every assistant invocation in your Gemini session and causes cross-topic context bleed.
3. **For state-changing operations (create / update / delete) put the full final payload in one message.** Do not split the work into a "draft" turn followed by a "confirm and apply" turn — if server-side context is lost between turns, the confirmation message itself can be persisted as the resource content.
4. **After any write, re-fetch the resource and verify the written content matches what you sent.** If it does not match, the call was lost — resend in single-shot form with the full payload.

Run CodeMie assistant chat with the user's message:

```bash
workflow_id="develpment-cs-$(date +%Y%m%d-%H%M%S)-$$"
codemie assistants chat "da55a74c-b6e9-4963-8d3d-e3ac9732e482" --conversation-id "$workflow_id" "message"
```

File attachments can be passed through the chat command with `--file` (reuse the same workflow id):

```bash
codemie assistants chat "da55a74c-b6e9-4963-8d3d-e3ac9732e482" --conversation-id "$workflow_id" "review this file" --file "path/to/file"
```
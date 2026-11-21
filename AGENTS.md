# Quality checks

Before considering any task complete, run all three quality checks below, even
for test-only, documentation, or configuration changes:

1. JUnit regression tests: `.\mvnw.cmd test`
2. PIT mutation tests: `.\mvnw.cmd test-compile org.pitest:pitest-maven:mutationCoverage`
3. SpotBugs analysis and findings check: `.\mvnw.cmd spotbugs:spotbugs spotbugs:check`

Run the commands from the repository root. On Bash or other Unix shells, use
`./mvnw` in place of `.\mvnw.cmd`.

Do not report a check as passing unless its Maven command exits successfully.
If a command cannot be run or fails, report that explicitly and do not claim
the quality checks all passed. Review SpotBugs findings and the PIT summary;
report surviving or timed-out mutants rather than implying they were killed.


# Changes review

Production-code changes should be clearly identified for human review.
Do not to weaken, remove, or disable tests simply to make the build pass.
Unless requested explicitly, preserve the existing behavior.


# Pushing and merging

Do not automatically push or merge changes.
Permission is necessary prior to pushing or merging any changes.
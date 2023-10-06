rem customize the tag name and message here
set TAG_NAME=fengguangyao
set MESSAGE="Personal Job"

rem create a branch
git branch %TAG_NAME%
git switch %TAG_NAME%
rem commit all changes
git add .
git commit -m %MESSAGE%
git push origin %TAG_NAME%
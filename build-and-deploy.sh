# TODO 写上自己的账号信息
DYCLOUD_EMAIL="sunhaoqingqa@gmail.com"
DYCLOUD_PWD="openqa1234"
APP_NAME="test_ai_app_lcx"
SERVICE_NAME="java-demo-qa"
DOCKER_REMARK="avatar demo"
ENV="dev" # 这里根据你的环境修改，dev 或者 prod

# 登录
dycloud login -e "${DYCLOUD_EMAIL}" -p "${DYCLOUD_PWD}"
# 切换应用和环境
dycloud env:switch --app-name "${APP_NAME}" --env "${ENV}"
# build 镜像
docker build --platform=linux/amd64 -t "${SERVICE_NAME}" .
# push 镜像
dycloud container:push --tag latest --service-name "${SERVICE_NAME}" --remark "${DOCKER_REMARK}"
# 部署 dev 环境
dycloud service:deploy -t latest --service-name "${SERVICE_NAME}" -n "${DOCKER_REMARK}"

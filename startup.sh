#!/usr/bin/env bash

set -euo pipefail

# === CONFIG ===
TOMCAT_HOME="${TOMCAT_HOME:-/opt/homebrew/Cellar/tomcat@10/10.1.48/libexec}"
CATALINA_BIN="$TOMCAT_HOME/bin/catalina.sh"
WEBAPPS="$TOMCAT_HOME/webapps"
WAR_NAME="cnw-cuoiky-1.0-SNAPSHOT.war"

if [ ! -x "$CATALINA_BIN" ]; then
  echo "❌ ERROR: Không tìm thấy catalina.sh tại $CATALINA_BIN"
  exit 1
fi

if ! command -v mvn >/dev/null 2>&1; then
  echo "❌ ERROR: Maven chưa được cài đặt (thiếu lệnh mvn)"
  exit 1
fi

echo "==> Dừng Tomcat (nếu đang chạy)..."
"$CATALINA_BIN" stop || true
sleep 2

echo "==> Dọn tiến trình Tomcat còn sót..."
pkill -f "org.apache.catalina.startup.Bootstrap" >/dev/null 2>&1 || true
sleep 1

echo "==> Đóng gói WAR..."
mvn clean package -q

if [ ! -f "target/$WAR_NAME" ]; then
  echo "❌ ERROR: Không tìm thấy WAR target/$WAR_NAME"
  exit 1
fi

echo "==> Gỡ bản deploy cũ..."
rm -rf "$WEBAPPS/cnw-cuoiky-1.0-SNAPSHOT" || true
rm -f "$WEBAPPS/$WAR_NAME" || true

echo "==> Copy WAR mới vào Tomcat..."
cp "target/$WAR_NAME" "$WEBAPPS/"

echo "==> Khởi động Tomcat (foreground, thoát bằng Ctrl+C)..."
exec "$CATALINA_BIN" run

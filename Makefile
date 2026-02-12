open-ios:
	open iosApp/iosApp.xcodeproj
	@echo finish $@.

setup-ios:
	sh scripts/setup-ios.sh
	@echo finish $@.
